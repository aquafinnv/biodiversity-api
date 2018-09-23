import { BioModule } from './bio.module';

describe('BioModule', () => {
  let bioModule: BioModule;

  beforeEach(() => {
    bioModule = new BioModule();
  });

  it('should create an instance', () => {
    expect(bioModule).toBeTruthy();
  });
});
