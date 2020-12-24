import Category from "./Category";
import bigDecimal from "js-big-decimal";

export default class Product {
    private _product: Product | undefined;
    private _name: string | undefined;
    private _price: bigDecimal | undefined;
    private _category: Category | undefined;

    constructor() {}

    get product() {
        return this._product;
    }

    set product(value) {
        this._product = value;
    }

    get name() {
        return this._name;
    }

    set name(value) {
        this._name = value;
    }

    get price() {
        return this._price;
    }

    set price(value) {
        this._price = value;
    }

    get category() {
        return this._category;
    }

    set category(value) {
        this._category = value;
    }
}