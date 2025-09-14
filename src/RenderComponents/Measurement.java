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
		result = convertValue();
	}
	
	public static Measurement Pixel() {
		return new Measurement(Measure.PX, 1);
	}
	
	public static Measurement MilliMeter() {
		return new Measurement(Measure.MM, 1);
	}
	
	public static Measurement CentiMeter() {
		return new Measurement(Measure.CM, 1);
	}
	
	public static Measurement Meter() {
		return new Measurement(Measure.M, 1);
	}
	
	public static Measurement Inch() {
		return new Measurement(Measure.IN, 1);
	}
	
	public static Measurement Point() {
		return new Measurement(Measure.PT, 1);
	}
	
	public static Measurement Picas() {
		return new Measurement(Measure.PC, 1);
	}
	
	public void setMeasurement(Measure measure) {
		this.measure = measure;
		result = convertValue();
	}
	
	public Measure getMeasurement() {
		return this.measure;
	}
	
	public void setValue(double value) {
		this.value = value;
		result = convertValue();
	}
	
	public double getValue() {
		return this.value;
	}
	
	public double getResult() {
		return this.result;
	}
	
	private double convert(double val, double convertFactor) {
		return val * convertFactor;
	}
	
	private double convertValue() {
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
		case PX:
			return getValue();
		case VW:
			return convert(getValue(), VW);
		case VH:
			return convert(getValue(), VH);
		case VMIN:
			return convert(getValue(), Math.min(VW, VH));
		case VMAX:
			return convert(getValue(), Math.max(VW, VH));
		
		default:
			return value;
		
		}
	}
}