describe('My First Test', () => {
	it('Visits the initial project page', () => {
		cy.visit('/');
		cy.contains('Barbie');
	});
});

describe('Change language', () => {
	it('Changes the language', () => {
		const languageSelect = '#language-select';
		cy.visit('/discover');
		cy.get(languageSelect).select('en');
		cy.wait(100);
		cy.contains('Login');
		cy.get(languageSelect).select('de');
		cy.wait(100);
		cy.contains('Anmelden');
	});
});
