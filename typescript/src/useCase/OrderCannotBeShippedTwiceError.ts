export default class OrderCannotBeShippedTwiceError implements Error{
    message: string = 'Order cannot be shipped twice error';
    name: string = 'OrderCannotBeShippedTwiceError';

}