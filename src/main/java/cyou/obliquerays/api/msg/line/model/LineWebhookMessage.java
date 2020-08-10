/**
 *  Copyright 2020 tasekida
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package cyou.obliquerays.api.msg.line.model;

import java.util.ArrayList;
import java.util.List;

public class LineWebhookMessage {
    //// Fields
    /**
     *  メッセージID
     */
    private String id;
    /**
     *  メッセージ情報のタイプを表す識別子
     */
    private String type;
    /**
     *  メッセージのテキスト
     */
    private String text;
    /**
     *  動画ファイルの長さ（ミリ秒）
     */
    private long duration;
    /**
     *  ファイル名
     */
    private String fileName;
    /**
     *  ファイルサイズ（バイト）
     */
    private long fileSize;
    /**
     *  タイトル
     */
    private String title;
    /**
     *  住所
     */
    private String address;
    /**
     *  緯度
     */
    private double latitude;
    /**
     *  経度
     */
    private double longitude;
    /**
     *  パッケージID
     */
    private String packageId;
    /**
     *  スタンプID
     */
    private String stickerId;
    /**
     *  スタンプのリソースタイプ
     */
    private String stickerResourceType;
    /**
     *  LINE絵文字オブジェクトの配列
     */
    private final List<LineWebhookEmoji> emojis = new ArrayList<>(0);
    /**
     *  画像を含むメッセージオブジェクトの配列
     */
    private final List<LineWebhookContentProvider> contentProvider = new ArrayList<>(0);

    //// Getters
    public String getId() {
        return this.id;
    }
    public String getType() {
        return this.type;
    }
    public String getText() {
        return this.text;
    }
    public long getDuration() {
        return this.duration;
    }
    public String getFileName() {
        return this.fileName;
    }
    public long getFileSize() {
        return this.fileSize;
    }
    public String getTitle() {
        return this.title;
    }
    public String getAddress() {
        return this.address;
    }
    public double getLatitude() {
        return this.latitude;
    }
    public double getLongitude() {
        return this.longitude;
    }
    public String getPackageId() {
        return this.packageId;
    }
    public String getStickerId() {
        return this.stickerId;
    }
    public String getStickerResourceType() {
        return this.stickerResourceType;
    }
    public List<LineWebhookEmoji> getEmojis() {
        return this.emojis;
    }
    public List<LineWebhookContentProvider> getContentProvider() {
        return this.contentProvider;
    }

    //// Setters
    public void setId(String _id) {
        this.id = _id;
    }
    public void setType(String _type) {
        this.type = _type;
    }
    public void setText(String _text) {
        this.text = _text;
    }
    public void setDuration(long _duration) {
        this.duration = _duration;
    }
    public void setFileName(String _fileName) {
        this.fileName = _fileName;
    }
    public void setFileSize(long _fileSize) {
        this.fileSize = _fileSize;
    }
    public void setTitle(String _title) {
        this.title = _title;
    }
    public void setAddress(String _address) {
        this.address = _address;
    }
    public void setLatitude(double _latitude) {
        this.latitude = _latitude;
    }
    public void setLongitude(double _longitude) {
        this.longitude = _longitude;
    }
    public void setPackageId(String _packageId) {
        this.packageId = _packageId;
    }
    public void setStickerId(String _stickerId) {
        this.stickerId = _stickerId;
    }
    public void setStickerResourceType(String _stickerResourceType) {
        this.stickerResourceType = _stickerResourceType;
    }
    public void setEmojis(List<LineWebhookEmoji> _emojis) {
        this.emojis.clear();
        if (!_emojis.isEmpty()) {
            this.emojis.addAll(_emojis);
        }
    }
    public void setContentProvider(List<LineWebhookContentProvider> _contentProvider) {
        this.contentProvider.clear();
        if (!_contentProvider.isEmpty()) {
            this.contentProvider.addAll(_contentProvider);
        }
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Message [id=").append(id).append(", type=").append(type)
                .append(", text=").append(text).append(", duration=").append(duration)
                .append(", fileName=").append(fileName).append(", fileSize=").append(fileSize)
                .append(", title=").append(title).append(", address=").append(address)
                .append(", latitude=").append(latitude).append(", longitude=").append(longitude)
                .append(", packageId=").append(packageId).append(", stickerId=").append(stickerId)
                .append(", stickerResourceType=").append(stickerResourceType).append(", emojis=").append(emojis)
                .append(", contentProvider=").append(contentProvider).append("]");
        return builder.toString();
    }
}
