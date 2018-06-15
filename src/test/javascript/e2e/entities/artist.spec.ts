import { browser, element, by } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';

describe('Artist e2e test', () => {

    let navBarPage: NavBarPage;
    let artistDialogPage: ArtistDialogPage;
    let artistComponentsPage: ArtistComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Artists', () => {
        navBarPage.goToEntity('artist');
        artistComponentsPage = new ArtistComponentsPage();
        expect(artistComponentsPage.getTitle())
            .toMatch(/Artists/);

    });

    it('should load create Artist dialog', () => {
        artistComponentsPage.clickOnCreateButton();
        artistDialogPage = new ArtistDialogPage();
        expect(artistDialogPage.getModalTitle())
            .toMatch(/Create or edit a Artist/);
        artistDialogPage.close();
    });

    it('should create and save Artists', () => {
        artistComponentsPage.clickOnCreateButton();
        artistDialogPage.setNameInput('name');
        expect(artistDialogPage.getNameInput()).toMatch('name');
        artistDialogPage.save();
        expect(artistDialogPage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class ArtistComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-artist div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getText();
    }
}

export class ArtistDialogPage {
    modalTitle = element(by.css('h4#myArtistLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    nameInput = element(by.css('input#field_name'));

    getModalTitle() {
        return this.modalTitle.getText();
    }

    setNameInput = function(name) {
        this.nameInput.sendKeys(name);
    };

    getNameInput = function() {
        return this.nameInput.getAttribute('value');
    };

    save() {
        this.saveButton.click();
    }

    close() {
        this.closeButton.click();
    }

    getSaveButton() {
        return this.saveButton;
    }
}
