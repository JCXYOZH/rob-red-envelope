public class RecordFragment extends BaseFragment {
    private ArrayAdapter<String> adapter;
    private ArrayList<String> records = new ArrayList<>();
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ListView listView = view.findViewById(R.id.lv_wechat_record);
        adapter = new ArrayAdapter<>(getContext(),
                R.layout.item_wechat_record,
                R.id.tv_item_wechat_record,
                records
        );
        listView.setAdapter(adapter);

        loadRecords();
    }

    private void loadRecords() {
        executor.execute(() -> {
            List<WechatRedEnvelope> data = WechatRedEnvelopeDb.queryAll();
            double total = calculateTotal(data);

            new Handler(Looper.getMainLooper()).post(() -> {
                records.clear();
                for (WechatRedEnvelope item : data) {
                    records.add(formatRecord(item));
                }
                updateTotalView(total);
                adapter.notifyDataSetChanged();
            });
        });
    }

    private double calculateTotal(List<WechatRedEnvelope> data) {
        return data.stream().mapToDouble(WechatRedEnvelope::getAmount).sum();
    }
}