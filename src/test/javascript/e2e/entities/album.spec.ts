import { browser, element, by } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';

describe('Album e2e test', () => {

    let navBarPage: NavBarPage;
    let albumDialogPage: AlbumDialogPage;
    let albumComponentsPage: AlbumComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Albums', () => {
        navBarPage.goToEntity('album');
        albumComponentsPage = new AlbumComponentsPage();
        expect(albumComponentsPage.getTitle())
            .toMatch(/Albums/);

    });

    it('should load create Album dialog', () => {
        albumComponentsPage.clickOnCreateButton();
        albumDialogPage = new AlbumDialogPage();
        expect(albumDialogPage.getModalTitle())
            .toMatch(/Create or edit a Album/);
        albumDialogPage.close();
    });

    it('should create and save Albums', () => {
        albumComponentsPage.clickOnCreateButton();
        albumDialogPage.setAlbumTypeInput('albumType');
        expect(albumDialogPage.getAlbumTypeInput()).toMatch('albumType');
        albumDialogPage.setNameInput('name');
        expect(albumDialogPage.getNameInput()).toMatch('name');
        albumDialogPage.setReleaseDateInput('releaseDate');
        expect(albumDialogPage.getReleaseDateInput()).toMatch('releaseDate');
        albumDialogPage.setArtistListInput('artistList');
        expect(albumDialogPage.getArtistListInput()).toMatch('artistList');
        albumDialogPage.save();
        expect(albumDialogPage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class AlbumComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-album div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getText();
    }
}

export class AlbumDialogPage {
    modalTitle = element(by.css('h4#myAlbumLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    albumTypeInput = element(by.css('input#field_albumType'));
    nameInput = element(by.css('input#field_name'));
    releaseDateInput = element(by.css('input#field_releaseDate'));
    artistListInput = element(by.css('input#field_artistList'));

    getModalTitle() {
        return this.modalTitle.getText();
    }

    setAlbumTypeInput = function(albumType) {
        this.albumTypeInput.sendKeys(albumType);
    };

    getAlbumTypeInput = function() {
        return this.albumTypeInput.getAttribute('value');
    };

    setNameInput = function(name) {
        this.nameInput.sendKeys(name);
    };

    getNameInput = function() {
        return this.nameInput.getAttribute('value');
    };

    setReleaseDateInput = function(releaseDate) {
        this.releaseDateInput.sendKeys(releaseDate);
    };

    getReleaseDateInput = function() {
        return this.releaseDateInput.getAttribute('value');
    };

    setArtistListInput = function(artistList) {
        this.artistListInput.sendKeys(artistList);
    };

    getArtistListInput = function() {
        return this.artistListInput.getAttribute('value');
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
