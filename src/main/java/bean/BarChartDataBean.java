package bean;

import org.chartistjsf.model.chart.*;
import org.primefaces.event.ItemSelectEvent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class BarChartDataBean {

    @Inject
    private DataBean dataBean;


    private BarChartModel barChartModel = null;
    public void createBarModel() {
        Long numberOfRows = dataBean.getNumberOfElements();

        barChartModel = new BarChartModel();
        barChartModel.setAspectRatio(AspectRatio.GOLDEN_SECTION);
        barChartModel.addLabel("Data1");
        barChartModel.addLabel("Data2");
        barChartModel.addLabel("Data3");
        barChartModel.addLabel("Data4");
        barChartModel.addLabel("Data5");
        BarChartSeries series1 = new BarChartSeries();
        series1.setName("Incoming");
        series1.set(19);
        series1.set(27);
        series1.set(12);
        series1.set(33);
        series1.set(35);
        BarChartSeries series2 = new BarChartSeries();
        series2.setName("Outgoing");
        series2.set(13);
        series2.set(35);
        series2.set(16);
        series2.set(28);
        series2.set(11);
        BarChartSeries series3 = new BarChartSeries();
        series3.setName("Missed");
        series3.set(18);
        series3.set(11);
        series3.set(21);
        series3.set(35);
        series3.set(35);
        BarChartSeries series4 = new BarChartSeries();
        series4.setName("Inbox SMS");
        series4.set(19);
        series4.set(27);
        series4.set(12);
        series4.set(33);
        series4.set(35);
        BarChartSeries series5 = new BarChartSeries();
        series5.setName("Outbox SMS");
        series5.set(13);
        series5.set(35);
        series5.set(16);
        series5.set(28);
        series5.set(11);
        Axis xAxis = barChartModel.getAxis(AxisType.X);
        xAxis.setShowGrid(false);
        barChartModel.addSeries(series1);
        barChartModel.addSeries(series2);
        barChartModel.addSeries(series3);
        barChartModel.addSeries(series4);
        barChartModel.addSeries(series5);
        barChartModel.setShowTooltip(true);
        barChartModel.setSeriesBarDistance(15);
        barChartModel.setAnimateAdvanced(false);
    }
    public BarChartModel getBarChartModel() {
        if (barChartModel == null) {
            createBarModel();
        }
        return barChartModel;
    }
    public void setBarChartModel(BarChartModel barChartModel) {
        this.barChartModel = barChartModel;
    }
    public void barItemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected", "Item Value: "
                + ((ChartSeries) barChartModel.getSeries().get(event.getSeriesIndex())).getData().get(
                event.getItemIndex()) + ", Series name:"
                + ((ChartSeries) barChartModel.getSeries().get(event.getSeriesIndex())).getName());
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
    }
}
