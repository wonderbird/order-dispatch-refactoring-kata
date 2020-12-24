import Product from "./Product";
import bigDecimal from "js-big-decimal";

export default class OrderItem {
    private _product: Product | undefined;
    private _quantity: number | undefined;
    private _taxedAmount: bigDecimal | undefined;
    private _tax: bigDecimal | undefined;

    constructor() {}

    get product() {
        return this._product;
    }

    set product(value) {
        this._product = value;
    }

    get quantity() {
        return this._quantity;
    }

    set quantity(value) {
        this._quantity = value;
    }

    get taxedAmount() {
        return this._taxedAmount;
    }

    set taxedAmount(value) {
        this._taxedAmount = value;
    }

    get tax() {
        return this._tax;
    }

    set tax(value) {
        this._tax = value;
    }
}