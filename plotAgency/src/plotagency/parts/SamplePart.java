package plotagency.parts;

import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.visualization.xygraph.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class SamplePart {

//	private TableViewer tableViewer;
//
//	@Inject
//	private MPart part;
//
//	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		Canvas canvas = new Canvas(parent, SWT.NONE);
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		LightweightSystem lws = new LightweightSystem(canvas);
		XYGraph xyGraph = new XYGraph();
		ToolbarArmedXYGraph toolbarArmedXYGraph = new ToolbarArmedXYGraph(xyGraph);
		lws.setContents(toolbarArmedXYGraph);
		CircularBufferDataProvider traceDataProviderTraining = new CircularBufferDataProvider(false);
		traceDataProviderTraining.setBufferSize(100);
		Trace traceTraining = new Trace("Trace legenda", xyGraph.getPrimaryXAxis(), xyGraph.getPrimaryYAxis(),traceDataProviderTraining);
		xyGraph.addTrace(traceTraining);
		xyGraph.setTitle("Sigmoid function");
		xyGraph.getPrimaryYAxis().setScaleLineVisible(true);
		xyGraph.getPrimaryXAxis().setShowMajorGrid(true);
		xyGraph.getPrimaryYAxis().setShowMajorGrid(true);
		xyGraph.getPrimaryXAxis().setVisible(true);
		traceTraining.setPointStyle(PointStyle.BAR);
		traceTraining.setTraceColor(XYGraphMediaFactory.getInstance().getColor(XYGraphMediaFactory.COLOR_RED));
		xyGraph.getPrimaryXAxis().setTitle("X axis");
		xyGraph.getPrimaryYAxis().setTitle("Y axis");
		xyGraph.getPrimaryYAxis().setDashGridLine(true);
		//Plot our xy function
		for (int x = -20; x &lt; 20; x++) {
		    double y = 1.0 / (1.0 + Math.exp(-x));
		    traceDataProviderTraining.addSample(new Sample(x,y));
		    xyGraph.performAutoScale();
		}
//
//		Text txtInput = new Text(parent, SWT.BORDER);
//		txtInput.setMessage("Enter text to mark part as dirty");
//		txtInput.addModifyListener(e -> part.setDirty(true));
//		txtInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//
//		tableViewer = new TableViewer(parent);
//
//		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
//		tableViewer.setInput(createInitialDataModel());
//		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
	}
//
//	@Focus
//	public void setFocus() {
//		tableViewer.getTable().setFocus();
//	}
//
//	@Persist
//	public void save() {
//		part.setDirty(false);
//	}
//	
//	private List<String> createInitialDataModel() {
//		return Arrays.asList("Sample item 1", "Sample item 2", "Sample item 3", "Sample item 4", "Sample item 5");
//	}
}