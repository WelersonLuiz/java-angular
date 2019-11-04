import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViagemFimComponent } from './viagem-fim.component';

describe('ViagemFimComponent', () => {
  let component: ViagemFimComponent;
  let fixture: ComponentFixture<ViagemFimComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViagemFimComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViagemFimComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
