export default class SellItemRequest {
    private readonly _quantity: number;
    private readonly _productName: string;

    constructor(product: string, _quantity: number) {
        this._productName = product;
        this._quantity = _quantity;
    }

    get quantity(): number {
        return this._quantity;
    }

    get productName(): string {
        return this._productName;
    }
}