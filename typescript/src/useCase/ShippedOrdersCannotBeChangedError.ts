export default class ShippedOrdersCannotBeChangedError implements Error {
    message: string = 'Shipped orders cannot be changed';
    name: string = 'ShippedOrdersCannotBeChangedError';
}