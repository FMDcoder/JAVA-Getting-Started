package RenderComponents;

import Modifiers.Measure;

public class Measurement {
	
	private final static double
		METRIC = 3.7795275591,
		MM_TO_PX = METRIC,
		CM_TO_PX = METRIC * 10,
		METER_TO_PX = METRIC * 1000,
		
		INCH_TO_PX = 96,
		PT_TO_PX = (1 / 4.0),
		PC_TO_PX = 16;
		
	
	private Measure measure;
	private double value, result;
	
	public Measurement(Measure measure, double value) {
		this.measure = measure;
		this.value = value;
	}
	
	public void setMeasurement(Measure measure) {
		this.measure = measure;
	}
	
	public Measure getMeasurement() {
		return this.measure;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return this.value;
	}
	
	private double convert(double val, double convertFactor) {
		return val * convertFactor;
	}
	
	private double convertValue(double value) {
		double VW = Dimension.getWindowWidth() * 0.01;
		double VH = Dimension.getWindowHeight() * 0.01;
		
		switch(measure) {
		case MM:
			return convert(getValue(), MM_TO_PX);
		case CM:
			return convert(getValue(), CM_TO_PX);
		case M:
			return convert(getValue(), METER_TO_PX);
		case IN:
			return convert(getValue(), INCH_TO_PX);
		case PT:
			return convert(getValue(), PT_TO_PX);
		case PC:
			return convert(getValue(), PC_TO_PX);
		default:
			return value;
		
		}
	}
}
