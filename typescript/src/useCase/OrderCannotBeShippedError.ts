export default class OrderCannotBeShippedError implements Error{
    message: string = 'Order cannot be shipped';
    name: string = 'OrderCannotBeShippedError';

}