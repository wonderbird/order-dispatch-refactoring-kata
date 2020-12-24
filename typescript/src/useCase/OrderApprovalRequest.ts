export default class OrderApprovalRequest {
    constructor(orderId: number, approved: boolean) {
        this._orderId = orderId;
        this._approved = approved;
    }

    private readonly _orderId: number;
    private readonly _approved: boolean;


    get orderId(): number {
        return this._orderId;
    }

    get approved(): boolean {
        return this._approved;
    }
}