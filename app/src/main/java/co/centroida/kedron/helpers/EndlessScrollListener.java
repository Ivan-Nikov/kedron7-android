package co.centroida.kedron.helpers;

import android.widget.AbsListView;

/**
 * Created by rimmustafin on 2/20/16.
 */
public class EndlessScrollListener implements AbsListView.OnScrollListener {
    private int currentPage = 0;
    private int previousTotal = 0;
    private boolean loading = true;
    private int threshold;
    private int maxSkip;
    private OnRequestEndlessListDataListener listener;

    public void reset(){
        currentPage = 0;
        previousTotal = 0;
    }

    public EndlessScrollListener(int threshold, int maxSkip, OnRequestEndlessListDataListener listener) {
        this.threshold = threshold;
        this.maxSkip = maxSkip;
        this.listener = listener;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + threshold)){
            loading = true;
            currentPage++;
            listener.onLoadMore(maxSkip, currentPage*maxSkip);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }
}
