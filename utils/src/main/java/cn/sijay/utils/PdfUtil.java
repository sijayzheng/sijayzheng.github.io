package cn.sijay.utils;

import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.*;
import lombok.Data;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <strong>PdfUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-07-20
 */
public class PdfUtil {
    public static void main(String[] args) throws IOException, DocumentException {
        String pdfPath = "D:\\temp\\test.pdf";
        String imgPath = "D:\\temp\\test.png";
        String newPdfPath = "D:\\temp\\new.pdf";
        String keyword = "签章位置";
        try (FileInputStream fileInputStream = new FileInputStream(pdfPath)) {
            byte[] pdfData = fileInputStream.readAllBytes();
            PdfReader reader = new PdfReader(pdfData);
            int pages = reader.getNumberOfPages();
            for (int pageNum = 1; pageNum <= pages; pageNum++) {
                PdfRenderListener pdfRenderListener = new PdfRenderListener(pageNum);
                PdfContentStreamProcessor processor = new PdfContentStreamProcessor(pdfRenderListener);
                try {
                    processor.processContent(ContentByteUtils.getContentBytesForPage(reader, pageNum), reader.getPageN(pageNum)
                                                                                                             .getAsDict(PdfName.RESOURCES));
                } catch (IOException e) {
                    reader.close();
                    throw e;
                }
                List<CharPosition> positionsList = pdfRenderListener.getCharPositions()
                                                                    .stream()
                                                                    .map(charPosition -> new CharPosition(charPosition.getPageNum(), charPosition.getX(), charPosition.getY(), charPosition.getCharWidth()))
                                                                    .toList();
                String content = pdfRenderListener.getContent();
                for (int pos = 0; pos < content.length(); pos++) {
                    int positionIndex = content.indexOf(keyword, pos);
                    if (positionIndex == -1) {
                        break;
                    }
                    CharPosition first = positionsList.get(positionIndex);
                    if (!Objects.isNull(first)) {
                        CharPosition position = new CharPosition(first.getPageNum(),
                                first.getX() + (keyword.length() * first.getCharWidth() / 2F),//图片坐标x
                                first.getY() - first.getCharWidth(),//图片坐标y
                                first.getCharWidth());
                        PdfReader pdfReader = new PdfReader(pdfPath);
                        System.out.println("x:" + position.getX() + " y:" + position.getY());
                        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(newPdfPath));
                        Image image = Image.getInstance(imgPath);
                        image.scaleAbsolute(100, 50);
                        image.setAbsolutePosition(position.getX(), position.getY());
                        System.out.println("pages:" + pdfReader.getNumberOfPages());
                        pdfStamper.getUnderContent(position.getPageNum()).addImage(image);
                        pdfStamper.close();
                        break;
                    }
                }
            }
            reader.close();
        }
    }

    private static class PdfRenderListener implements RenderListener {
        private final int pageNum;
        private final StringBuilder contentBuilder = new StringBuilder();
        @Getter
        private final List<CharPosition> charPositions = new ArrayList<>();

        public PdfRenderListener(int pageNum) {
            this.pageNum = pageNum;
        }

        @Override
        public void beginTextBlock() {
        }

        @Override
        public void renderText(TextRenderInfo renderInfo) {
            List<TextRenderInfo> characterRenderInfos = renderInfo.getCharacterRenderInfos();
            for (TextRenderInfo textRenderInfo : characterRenderInfos) {
                String word = textRenderInfo.getText();
                if (word.length() > 1) {
                    word = word.substring(word.length() - 1);
                }
                Rectangle2D.Float rectangle = textRenderInfo.getAscentLine().getBoundingRectange();
                float x = (float) rectangle.getMinX();
                float y = (float) rectangle.getMinY();
                float charWidth = (float) (rectangle.getMaxX() - rectangle.getMinX());
                CharPosition charPosition = new CharPosition(pageNum, x, y, charWidth);
                charPositions.add(charPosition);
                contentBuilder.append(word);
            }
        }

        @Override
        public void endTextBlock() {
        }

        @Override
        public void renderImage(ImageRenderInfo renderInfo) {
        }

        public String getContent() {
            return contentBuilder.toString();
        }

    }

    @Data
    public static final class CharPosition {
        private final int pageNum;
        private final float x;
        private final float y;
        private final float charWidth;

        public CharPosition(int pageNum, float x, float y, float charWidth) {
            this.pageNum = pageNum;
            this.x = x;
            this.y = y;
            this.charWidth = charWidth;
        }

    }
}

