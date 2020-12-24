import OrderItem from "./OrderItem";
import OrderStatus from "./OrderStatus";
import bigDecimal from "js-big-decimal";

export default class Order {
    private _total: bigDecimal | undefined;
    private _currency: string | undefined;
    private _items: OrderItem[] = [];
    private _tax: bigDecimal | undefined;
    private _status: OrderStatus;
    private _id: number | undefined;

    constructor(status = OrderStatus.CREATED, id? : number) {
        this._id = id;
        this._status = status
    }

    get total() {
        return this._total;
    }

    set total(value) {
        this._total = value;
    }

    get currency() {
        return this._currency;
    }

    set currency(value) {
        this._currency = value;
    }

    get items() {
        return this._items;
    }

    set items(value) {
        this._items = value;
    }

    get tax() {
        return this._tax;
    }

    set tax(value) {
        this._tax = value;
    }

    get status() {
        return this._status;
    }

    set status(value) {
        this._status = value;
    }

    get id() {
        return this._id;
    }

    set id(value) {
        this._id = value;
    }
}