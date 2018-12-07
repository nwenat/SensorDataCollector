package bean;

import data.UserMetrics;
import org.chartistjsf.model.chart.*;
import org.primefaces.event.ItemSelectEvent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

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

        BarChartSeries series1 = new BarChartSeries();
        series1.setName("Incoming");
        BarChartSeries series2 = new BarChartSeries();
        series2.setName("Outgoing");
        BarChartSeries series3 = new BarChartSeries();
        series3.setName("Missed");
        BarChartSeries series4 = new BarChartSeries();
        series4.setName("Inbox SMS");
        BarChartSeries series5 = new BarChartSeries();
        series5.setName("Outbox SMS");

        List<UserMetrics> userMetricsList = dataBean.getUserMetrics();

        for(UserMetrics metric : userMetricsList) {

            barChartModel.addLabel(metric.getDataString());
            series1.set(metric.getIncoming());
            series2.set(metric.getOutgoing());
            series3.set(metric.getMissed());
            series4.set(metric.getInboxSMS());
            series5.set(metric.getOutboxSMS());
        }

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
