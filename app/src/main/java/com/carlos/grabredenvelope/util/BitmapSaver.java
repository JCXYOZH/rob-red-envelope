public class BitmapSaver {
    public static void saveWechatQR(Context context, ImageView iv) {
        File dir = new File(Environment.getExternalStorageDirectory(), "GrabRedEnvelope");
        if (!dir.exists() && !dir.mkdirs()) {
            Toast.makeText(context, "创建目录失败", Toast.LENGTH_SHORT).show();
            return;
        }

        Bitmap bitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();
        File file = new File(dir, "xbd_wechat.jpg");

        try (FileOutputStream fos = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            Toast.makeText(context, "保存至:" + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            LogUtils.e("保存图片失败", e);
        }
    }
}