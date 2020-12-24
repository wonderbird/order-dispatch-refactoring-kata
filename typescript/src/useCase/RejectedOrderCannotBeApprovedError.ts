export default class RejectedOrderCannotBeApprovedError implements Error {
    message: string = 'Rejected order cannot be approved';
    name: string = 'RejectedOrderCannotBeApprovedError';
}