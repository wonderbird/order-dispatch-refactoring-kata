import bigDecimal from "js-big-decimal";

export default class Category {
    private _name: string;
    private readonly _taxPercentage: bigDecimal;

    constructor(name: string, taxPercentage: bigDecimal) {
        this._name = name;
        this._taxPercentage = taxPercentage;
    }

    get name() {
        return this._name;
    }

    get taxPercentage() {
        return this._taxPercentage;
    }
}