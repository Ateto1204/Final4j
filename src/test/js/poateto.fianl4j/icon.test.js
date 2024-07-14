import fetch from 'node-fetch';
global.fetch = fetch;

import { isUserExisted, findUserByEmail, showLogin } from '../../../main/resources/static/js/login';

// 在每個測試之前設置必要的 DOM 結構
beforeEach(() => {
  document.body.innerHTML = `
    <div id="login-div" style="display: none;"></div>
    <div id="register-div" style="display: none;"></div>
    <button id="login-toggle" class="active"></button>
    <button id="register-toggle"></button>
  `;
});

test('should check if user exists', async () => {
    const email = 'test@example.com';
    const result = await isUserExisted(email);
    expect(result).toBeDefined();
});

test('should find user by email and password', async () => {
    const email = 'test@example.com';
    const password = 'password';
    const result = await findUserByEmail(email, password);
    expect(result).toBeDefined();
});

test('should show login form', () => {
    showLogin();
    expect(document.getElementById('login-div').style.display).toBe('block');
    expect(document.getElementById('register-div').style.display).toBe('none');
    expect(document.getElementById('login-toggle').classList.contains('active')).toBe(true);
    expect(document.getElementById('register-toggle').classList.contains('active')).toBe(false);
});