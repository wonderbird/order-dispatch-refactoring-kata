import Order from "../../src/domain/Order";
import OrderShipmentRequest from "../../src/useCase/OrderShipmentRequest";
import TestOrderRepository from "../doubles/TestOrderRepository";
import OrderShipmentUseCase from "../../src/useCase/OrderShipmentUseCase";
import OrderCannotBeShippedError from "../../src/useCase/OrderCannotBeShippedError";
import OrderCannotBeShippedTwiceError from "../../src/useCase/OrderCannotBeShippedTwiceError";
import OrderStatus from "../../src/domain/OrderStatus";
import TestShipmentService from "../doubles/TestShipmentService";

describe('OrderShipmentUseCase should', () => {
    let orderRepository: TestOrderRepository;
    let shipmentService: TestShipmentService;
    let useCase: OrderShipmentUseCase;

    beforeEach(() => {
        orderRepository = new TestOrderRepository();
        shipmentService = new TestShipmentService();
        useCase = new OrderShipmentUseCase(orderRepository, shipmentService);
    });

    test('ship approved order', () => {
        let initialOrder = new Order(OrderStatus.APPROVED, 1);
        orderRepository.addOrder(initialOrder);

        useCase.run(new OrderShipmentRequest(1));

        expect(orderRepository.getSavedOrder()?.status).toBe(OrderStatus.SHIPPED);
        expect(shipmentService.getShippedOrder()).toBe(initialOrder);
    });

    test('not ship created orders', () => {
        orderRepository.addOrder(new Order(OrderStatus.CREATED, 1));

        expect(() => {
            useCase.run(new OrderShipmentRequest(1))
        }).toThrowError(OrderCannotBeShippedError);
        expect(orderRepository.getSavedOrder()).toBeUndefined();
        expect(shipmentService.getShippedOrder()).toBeUndefined();
    });

    test('not ship rejected orders', () => {
        orderRepository.addOrder(new Order(OrderStatus.REJECTED, 1));

        let request = new OrderShipmentRequest(1);

        expect(() => {
            useCase.run(request)
        }).toThrowError(OrderCannotBeShippedError);
        expect(orderRepository.getSavedOrder()).toBeUndefined();
        expect(shipmentService.getShippedOrder()).toBeUndefined();
    });

    test('not ship shipped orders', () => {
        orderRepository.addOrder(new Order(OrderStatus.SHIPPED, 1));

        expect(() => {
            useCase.run(new OrderShipmentRequest(1))
        }).toThrowError(OrderCannotBeShippedTwiceError);
        expect(orderRepository.getSavedOrder()).toBeUndefined();
        expect(shipmentService.getShippedOrder()).toBeUndefined();
    });
});
