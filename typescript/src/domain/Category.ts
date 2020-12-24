import bigDecimal from "js-big-decimal";

export default class Category {
    private _name: string | undefined;
    private _taxPercentage: bigDecimal | undefined;

    constructor(name: string, taxPercentage: bigDecimal) {
        this._name = name;
        this._taxPercentage = taxPercentage;
    }

    get name() {
        return this._name;
    }

    set name(name) {
        this._name = name;
    }


    get taxPercentage() {
        return this._taxPercentage;
    }

    set taxPercentage(taxPercentage) {
        this._taxPercentage = taxPercentage;
    }
}