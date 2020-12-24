export default class ApprovedOrderCannotBeRejectedError implements Error {
    name: string = 'ApprovedOrderCannotBeRejected'
    message: string = 'approved order cannot be rejected';
}