import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MultiTravelComponent } from './multi-travel.component';

describe('MultiTravelComponent', () => {
  let component: MultiTravelComponent;
  let fixture: ComponentFixture<MultiTravelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MultiTravelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MultiTravelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
