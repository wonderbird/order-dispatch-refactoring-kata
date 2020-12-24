export default class OrderShipmentRequest {
    private readonly _orderId: number;

    constructor(orderId: number) {
        this._orderId = orderId
    }

    get orderId(): number {
        return this._orderId;
    }
}