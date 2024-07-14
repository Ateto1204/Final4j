import fetchMock from 'jest-fetch-mock';
fetchMock.enableMocks();

import { isUserExisted, findUserByEmail, showLogin, start } from '../../../main/resources/static/js/login';

beforeEach(() => {
    fetch.resetMocks();
    document.body.innerHTML = `
        <div id="login-div" style="display: none;"></div>
        <div id="register-div" style="display: none;"></div>
        <button id="login-toggle" class="active"></button>
        <button id="register-toggle"></button>
        <form id="login-form"></form>
        <form id="register-form"></form>
    `;
});

test('should check if user exists', async () => {
    fetch.mockResponseOnce(JSON.stringify(true));

    const email = 'test@example.com';
    const result = await isUserExisted(email);
    expect(result).toBe(true);
});

test('should find user by email and password', async () => {
    fetch.mockResponseOnce(JSON.stringify({ name: 'Test User', email: 'test@example.com', pwd: 'password' }));

    const email = 'test@example.com';
    const password = 'password';
    const result = await findUserByEmail(email, password);
    expect(result.name).toBe('Test User');
});

test('should show login form', () => {
    showLogin();
    expect(document.getElementById('login-div').style.display).toBe('block');
    expect(document.getElementById('register-div').style.display).toBe('none');
    expect(document.getElementById('login-toggle').classList.contains('active')).toBe(true);
    expect(document.getElementById('register-toggle').classList.contains('active')).toBe(false);
});