import Order from "../../src/domain/Order";
import OrderStatus from "../../src/domain/OrderStatus"
import OrderApprovalRequest from "../../src/useCase/OrderApprovalRequest";
import TestOrderRepository from "../doubles/TestOrderRepository";
import OrderApprovalUseCase from "../../src/useCase/OrderApprovalUseCase";
import RejectedOrderCannotBeApprovedException from "../../src/useCase/RejectedOrderCannotBeApprovedException";
import ApprovedOrderCannotBeRejectedError from "../../src/useCase/ApprovedOrderCannotBeRejectedError";
import ShippedOrdersCannotBeChangedException from "../../src/useCase/ShippedOrdersCannotBeChangedException";

describe('OrderApprovalUseCase should', () => {
    let orderRepository: TestOrderRepository;
    let useCase: OrderApprovalUseCase;

    beforeEach(() => {
        orderRepository = new TestOrderRepository();
        useCase = new OrderApprovalUseCase(orderRepository);
    });

    test('approve existing order', () => {
        orderRepository.addOrder(new Order(OrderStatus.CREATED, 1));

        useCase.run(new OrderApprovalRequest(1, true));

        const savedOrder = orderRepository.getSavedOrder();
        expect(savedOrder.status).toBe(OrderStatus.APPROVED);
    });

    test('reject existing order', () => {
        orderRepository.addOrder(new Order(OrderStatus.CREATED, 1));

        useCase.run(new OrderApprovalRequest(1, false));

        const savedOrder = orderRepository.getSavedOrder();
        expect(savedOrder.status).toBe(OrderStatus.REJECTED);
    });

    test('not approve rejected order', () => {
        orderRepository.addOrder(new Order(OrderStatus.REJECTED, 1));

        expect(() => {useCase.run(new OrderApprovalRequest(1, true))}).toThrowError(RejectedOrderCannotBeApprovedException);
        expect(orderRepository.getSavedOrder()).toBeUndefined();
    });

    test('not reject approved order', () => {
        orderRepository.addOrder(new Order(OrderStatus.APPROVED, 1));

        expect(() => {useCase.run(new OrderApprovalRequest(1, false))}).toThrowError(ApprovedOrderCannotBeRejectedError);
        expect(orderRepository.getSavedOrder()).toBeUndefined();
    });

    test('not approve shipped orders', () => {
        orderRepository.addOrder(new Order(OrderStatus.SHIPPED, 1));

        expect(() => {useCase.run(new OrderApprovalRequest(1, true))}).toThrowError(ShippedOrdersCannotBeChangedException);
        expect(orderRepository.getSavedOrder()).toBeUndefined();
    });

    test('not reject shipped orders', () => {
        orderRepository.addOrder(new Order(OrderStatus.SHIPPED, 1));

        expect(() => {useCase.run(new OrderApprovalRequest(1, false))}).toThrowError(ShippedOrdersCannotBeChangedException);
        expect(orderRepository.getSavedOrder()).toBeUndefined();
    });

});
