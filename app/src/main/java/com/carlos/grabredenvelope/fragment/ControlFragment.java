public class ControlFragment extends BaseFragment implements SeekBar.OnSeekBarChangeListener {
    private WechatControlVO controlVO = new WechatControlVO();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CheckBox notificationCheck = view.findViewById(R.id.cb_wechat_notification_control);
        notificationCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            controlVO.setMonitorNotification(isChecked);
            RedEnvelopePreferences.setWechatControl(controlVO);
        });

        SeekBar openDelayBar = view.findViewById(R.id.sb_qq_putong);
        openDelayBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.sb_qq_putong) {
            TextView delayText = getView().findViewById(R.id.tv_qq_putong);
            delayText.setText("领取延迟：" + progress + "秒");
            controlVO.setDelayOpenTime(progress);
            RedEnvelopePreferences.setWechatControl(controlVO);
        }
    }
}