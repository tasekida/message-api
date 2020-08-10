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

public class LineWebhookEmoji {
    //// Fields
    /**
     * テキストの先頭を0とした、textプロパティ内の絵文字の開始位置
     */
    private int index;
    /**
     * LINE絵文字の文字列の長さ。LINE絵文字(hello)であれば、7が長さになります
     */
    private int length;
    /**
     * LINE絵文字の集合を示すプロダクトID
     */
    private String productId;
    /**
     * プロダクトID内のLINE絵文字のID
     */
    private String emojiId;

    //// Getters
    public int getIndex() {
        return this.index;
    }
    public int getLength() {
        return this.length;
    }
    public String getProductId() {
        return this.productId;
    }
    public String getEmojiId() {
        return this.emojiId;
    }

    //// Setters
    public void setIndex(int _index) {
        this.index = _index;
    }
    public void setLength(int _length) {
        this.length = _length;
    }
    public void setProductId(String _productId) {
        this.productId = _productId;
    }
    public void setEmojiId(String _emojiId) {
        this.emojiId = _emojiId;
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Emoji [index=").append(index)
                .append(", length=").append(length)
                .append(", productId=").append(productId)
                .append(", emojiId=").append(emojiId).append("]");
        return builder.toString();
    }
}
