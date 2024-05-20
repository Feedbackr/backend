/** @type {import('tailwindcss').Config} */
module.exports = {
	darkMode: 'class',
	content: ['./src/main/resources/templates/**/*.{html,js}', './node_modules/flowbite/**/*.js'],
	theme: {
		extend: {},
	},
	plugins: [require('flowbite/plugin')],
};
