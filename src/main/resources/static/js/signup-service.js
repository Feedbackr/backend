const submitFormBtn = document.getElementById('submitForm');

const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirm_password');

const companyNameEl = document.getElementById('company');
const emailEl = document.getElementById('email');
const companyWebsiteEl = document.getElementById('website');

const tipsEl = document.getElementById('tips');

function checkPasswordStrength(password) {
	// Initialize variables
	var strength = 0;
	var tips = '';

	// Check password length
	if (password.length < 8) {
		tips += '  ● Make the password longer.\n';
	} else {
		strength += 1;
	}

	// Check for mixed case
	if (password.match(/[a-z]/) && password.match(/[A-Z]/)) {
		strength += 1;
	} else {
		tips += '  ● Use both lowercase and uppercase letters.\n';
	}

	// Check for numbers
	if (password.match(/\d/)) {
		strength += 1;
	} else {
		tips += '  ● Include at least one number.\n';
	}

	// Check for special characters
	if (password.match(/[^a-zA-Z\d]/)) {
		strength += 1;
	} else {
		tips += '  ● Include at least one special character.\n';
	}

	// Return results
	if (strength < 2) {
		return 'Easy to guess.\n' + tips;
	} else if (strength === 2) {
		return 'Medium difficulty.\n' + tips;
	} else if (strength === 3) {
		return 'Difficult.\n' + tips;
	} else {
		return 'Extremely difficult.\n' + tips;
	}
}

password.addEventListener('keyup', (event) => {
	tipsEl.textContent = checkPasswordStrength(password.value);
});

function validatePasswords() {
	return password.value == confirmPassword.value;
}

function companyWithNameExists() {
	let name = companyNameEl.value;
	const url = 'http://localhost:8080/api/check/company_name/' + name;
	const xhr = new XMLHttpRequest();
	xhr.open('GET', url, false);
	try {
		xhr.send();
	} catch (e) {
		console.log('error');
	}
	return xhr.status == 200;
}

function emailExists() {
	let email = emailEl.value;
	const url = 'http://localhost:8080/api/check/email/' + email;
	const xhr = new XMLHttpRequest();
	xhr.open('GET', url, false);
	try {
		xhr.send();
	} catch (e) {
		console.log('error');
	}
	return xhr.status == 200;
}

function websiteExists() {
	let host = new URL(companyWebsiteEl.value).host;
	const url = 'http://localhost:8080/api/check/company_website/' + host;
	const xhr = new XMLHttpRequest();
	xhr.open('GET', url, false);
	try {
		xhr.send();
	} catch (e) {
		console.log('error');
	}
	return xhr.status == 200;
}

function validateForm() {
	if (!validatePasswords()) {
		alert('Passwords do not match!');
		return false;
	}
	if (companyWithNameExists()) {
		alert('Company with that name already exists!');
		return false;
	}
	if (emailExists()) {
		alert('User with that email already exists!');
		return false;
	}
	if (websiteExists()) {
		alert('Company with that website already exists!');
		return false;
	}
	return true;
}

function submitForm() {
	if (validateForm()) {
		submitFormBtn.click();
	}
}
