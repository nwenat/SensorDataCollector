package bean;

import data.UserMetrics;
import org.chartistjsf.model.chart.*;
import org.primefaces.event.ItemSelectEvent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.List;

@Named
@RequestScoped
public class LineChartBean {

    @Inject
    private DataBean dataBean;

    private LineChartModel lineChartModel = null;
    private LocalDateTime afterDate = null;
    private LocalDateTime beforeDate = null;
    private List<UserMetrics> userMetricsList = null;

    public void createLineModel() {

        lineChartModel = new LineChartModel();
        lineChartModel.setAspectRatio(AspectRatio.GOLDEN_SECTION);

        LineChartSeries lineChartSeries1 = new LineChartSeries();
        lineChartSeries1.setName("Incoming");
        LineChartSeries lineChartSeries2 = new LineChartSeries();
        lineChartSeries2.setName("Outgoing");
        LineChartSeries lineChartSeries3 = new LineChartSeries();
        lineChartSeries3.setName("Missed");
        LineChartSeries lineChartSeries4 = new LineChartSeries();
        lineChartSeries4.setName("Inbox SMS");
        LineChartSeries lineChartSeries5 = new LineChartSeries();
        lineChartSeries5.setName("Outbox SMS");

        if (afterDate == null && beforeDate == null) {
            userMetricsList = dataBean.getUserMetrics();
        } else if (afterDate != null && beforeDate != null) {
            userMetricsList = dataBean.getMetricsBetween(afterDate, beforeDate);
        } else if (afterDate != null && beforeDate == null) {
            userMetricsList = dataBean.getMetricsAfter(afterDate);
        } else if (afterDate == null && beforeDate != null) {
            userMetricsList = dataBean.getMetricsBefore(beforeDate);
        }

        if(userMetricsList != null) {
            for (UserMetrics metric : userMetricsList) {

                lineChartModel.addLabel(metric.getDataString());
                lineChartSeries1.set(metric.getIncoming());
                lineChartSeries2.set(metric.getOutgoing());
                lineChartSeries3.set(metric.getMissed());
                lineChartSeries4.set(metric.getInboxSMS());
                lineChartSeries5.set(metric.getOutboxSMS());
            }
        }

        Axis xAxis = lineChartModel.getAxis(AxisType.X);
        Axis yAxis = lineChartModel.getAxis(AxisType.Y);
        yAxis.setScaleMinSpace(100);
        lineChartModel.addSeries(lineChartSeries1);
        lineChartModel.addSeries(lineChartSeries2);
        lineChartModel.addSeries(lineChartSeries3);
        lineChartModel.addSeries(lineChartSeries4);
        lineChartModel.addSeries(lineChartSeries5);
        lineChartModel.setAnimateAdvanced(false);
        lineChartModel.setShowTooltip(true);
        lineChartModel.setLineSmooth(false);
    }
    public LineChartModel getLineChartModel() {
        if (lineChartModel == null) {
            createLineModel();
        }
        return lineChartModel;
    }

    public void setLineChartModel(LineChartModel lineModel) {
        this.lineChartModel = lineModel;
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected", "Item Value: "+
                ((ChartSeries) lineChartModel.getSeries().get(event.getSeriesIndex())).getData().get(event.getItemIndex())
                + ", Series name:" +
                ((ChartSeries) lineChartModel.getSeries().get(event.getSeriesIndex())).getName());
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
    }

    public LocalDateTime getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(LocalDateTime afterDate) {
        this.afterDate = afterDate;
    }

    public LocalDateTime getBeforeDate() {
        return beforeDate;
    }

    public void setBeforeDate(LocalDateTime beforeDate) {
        this.beforeDate = beforeDate;
    }
}
