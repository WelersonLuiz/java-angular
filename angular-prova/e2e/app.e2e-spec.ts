import { AngularProvaPage } from './app.po';

describe('angular-prova App', function() {
  let page: AngularProvaPage;

  beforeEach(() => {
    page = new AngularProvaPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
