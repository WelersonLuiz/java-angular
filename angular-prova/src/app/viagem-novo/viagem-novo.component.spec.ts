import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { ViagemNovoComponent } from './viagem-novo.component';

describe('ViagemNovoComponent', () => {
  let component: ViagemNovoComponent;
  let fixture: ComponentFixture<ViagemNovoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViagemNovoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViagemNovoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
