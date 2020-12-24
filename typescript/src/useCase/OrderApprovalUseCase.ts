import OrderRepository from "../repository/OrderRepository";
import OrderApprovalRequest from "./OrderApprovalRequest";
import ApprovedOrderCannotBeRejectedError from "./ApprovedOrderCannotBeRejectedError";
import ShippedOrdersCannotBeChangedError from "./ShippedOrdersCannotBeChangedError";
import RejectedOrderCannotBeApprovedError from "./RejectedOrderCannotBeApprovedError";
import OrderStatus from "../domain/OrderStatus";

export default class OrderApprovalUseCase {
    private readonly _orderRepository: OrderRepository;

    constructor(orderRepository: OrderRepository) {
        this._orderRepository = orderRepository;
    }

    public run(request: OrderApprovalRequest): void {
        const order = this._orderRepository.getById(request.orderId);
        if (order.status === OrderStatus.SHIPPED) {
            throw new ShippedOrdersCannotBeChangedError();
        }

        if (request.approved && order.status === OrderStatus.REJECTED) {
            throw new RejectedOrderCannotBeApprovedError();
        }

        if (!request.approved && order.status === OrderStatus.APPROVED) {
            throw new ApprovedOrderCannotBeRejectedError();
        }

        order.status = request.approved ? OrderStatus.APPROVED : OrderStatus.REJECTED;
        this._orderRepository.save(order);
    }
}