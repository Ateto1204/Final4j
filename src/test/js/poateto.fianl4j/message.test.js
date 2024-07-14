const { isMessage, toMessage } = require('../../../main/resources/static/js/message');

test('should identify a valid message', () => {
    const message = 'Hello world';
    expect(isMessage(message)).toBe(true);
});

test('should identify an invalid message', () => {
    const message = '     ';
    expect(isMessage(message)).toBe(false);
});

test('should convert message with newline to HTML', () => {
    const message = 'Hello\nworld';
    expect(toMessage(message)).toBe('Hello<br>world');
});