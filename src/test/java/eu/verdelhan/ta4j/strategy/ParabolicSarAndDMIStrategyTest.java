package eu.verdelhan.ta4j.strategy;

import eu.verdelhan.ta4j.strategy.IndicatorOverIndicatorStrategy;
import eu.verdelhan.ta4j.strategy.IndicatorCrossedIndicatorStrategy;
import eu.verdelhan.ta4j.strategy.ParabolicSarAndDMIStrategy;
import eu.verdelhan.ta4j.TimeSeries;
import eu.verdelhan.ta4j.indicator.simple.ClosePrice;
import eu.verdelhan.ta4j.mocks.MockTimeSeries;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class ParabolicSarAndDMIStrategyTest {

	@Test
	public void shouldEnterTest()
	{
		TimeSeries series1 = new MockTimeSeries(new double[] {10, 9, 6, 10, 5 });
		TimeSeries series2 = new MockTimeSeries(new double[] {8, 7, 7, 8, 6 });
		
		IndicatorCrossedIndicatorStrategy indicatorCrossedIndicator = new IndicatorCrossedIndicatorStrategy(new ClosePrice(series1), new ClosePrice(series2));
		ParabolicSarAndDMIStrategy parabolicStrategy = new ParabolicSarAndDMIStrategy(indicatorCrossedIndicator, null);
		assertFalse(parabolicStrategy.shouldEnter(0));
		assertFalse(parabolicStrategy.shouldEnter(1));
		assertTrue(parabolicStrategy.shouldEnter(2));
		assertFalse(parabolicStrategy.shouldEnter(3));
		assertTrue(parabolicStrategy.shouldEnter(4));
	}
	
	@Test
	public void shouldExitTest()
	{
		TimeSeries series1 = new MockTimeSeries(new double[] {6, 11, 6, 5, 9 });
		TimeSeries series2 = new MockTimeSeries(new double[] {10, 9, 7, 6, 6 });
		
		TimeSeries series3 = new MockTimeSeries(new double[] {1, 1, 1, 1, 1} );
		TimeSeries series4 = new MockTimeSeries(new double[] {2, 2, 2, 2, 0} );
		
		IndicatorCrossedIndicatorStrategy indicatorCrossedIndicator = new IndicatorCrossedIndicatorStrategy(new ClosePrice(series1), new ClosePrice(series2));
		IndicatorOverIndicatorStrategy indicatorOverIndicator = new IndicatorOverIndicatorStrategy(new ClosePrice(series3), new ClosePrice(series4));
		
		ParabolicSarAndDMIStrategy parabolicStrategy = new ParabolicSarAndDMIStrategy(indicatorCrossedIndicator, indicatorOverIndicator);
		
		assertFalse(parabolicStrategy.shouldExit(0));
		assertFalse(parabolicStrategy.shouldExit(1));
		assertFalse(parabolicStrategy.shouldExit(2));
		assertFalse(parabolicStrategy.shouldExit(3));
		assertTrue(parabolicStrategy.shouldExit(4));
	}
}