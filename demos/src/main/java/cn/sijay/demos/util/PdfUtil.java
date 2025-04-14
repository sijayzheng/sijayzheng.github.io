package cn.sijay.demos.util;

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
 * @author Sijay
 * @since 2024-07-20
 */
public class PdfUtil {
    public static void main(String[] args) {
        String pdfPath = "D:\\temp\\test.pdf";
        String imgPath = "D:\\temp\\test.png";
        String newPdfPath = "D:\\temp\\new.pdf";
        String keyword = "签章位置";
        try (FileInputStream fileInputStream = new FileInputStream(pdfPath); FileOutputStream fos = new FileOutputStream(newPdfPath)) {
            PdfReader reader = new PdfReader(fileInputStream.readAllBytes());
            int pages = reader.getNumberOfPages();
            for (int pageNum = 1; pageNum <= pages; pageNum++) {
                PdfRenderListener pdfRenderListener = new PdfRenderListener(pageNum);
                new PdfContentStreamProcessor(pdfRenderListener)
                        .processContent(ContentByteUtils.getContentBytesForPage(reader, pageNum), reader.getPageN(pageNum)
                                                                                                        .getAsDict(PdfName.RESOURCES));
                List<CharPosition> positionsList = pdfRenderListener.getCharPositions().stream()
                                                                    .map(charPosition -> new CharPosition(charPosition.getPageNum(), charPosition.getX(), charPosition.getY(), charPosition.getCharWidth()))
                                                                    .toList();
                String content = pdfRenderListener.getContent();
                for (int pos = 0; pos < content.length(); pos++) {
                    int positionIndex = content.indexOf(keyword, pos);
                    if (positionIndex == -1) {
                        break;
                    }
                    CharPosition charPosition = positionsList.get(positionIndex);
                    if (!Objects.isNull(charPosition)) {
                        CharPosition position = new CharPosition(charPosition.getPageNum(), charPosition.getX() + (keyword.length() * charPosition.getCharWidth()),//图片坐标x
                                charPosition.getY() - charPosition.getCharWidth() - charPosition.getCharWidth(),//图片坐标y
                                charPosition.getCharWidth());
                        PdfReader pdfReader = new PdfReader(pdfPath);
                        System.out.println("x:" + position.getX() + " y:" + position.getY());
                        PdfStamper pdfStamper = new PdfStamper(pdfReader, fos);
                        Image image = Image.getInstance(imgPath);
                        image.setAbsolutePosition(position.getX().floatValue(), position.getY().floatValue());
                        System.out.println("pages:" + pdfReader.getNumberOfPages());
                        pdfStamper.getUnderContent(position.getPageNum()).addImage(image);
                        pdfStamper.close();
                        break;
                    }
                }
            }
            reader.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
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
                CharPosition charPosition = new CharPosition(pageNum, rectangle.getMinX(), rectangle.getMinY(), rectangle.getMaxX() - rectangle.getMinX());
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
        private final Double x;
        private final Double y;
        private final Double charWidth;

        public CharPosition(int pageNum, double x, double y, double charWidth) {
            this.pageNum = pageNum;
            this.x = x;
            this.y = y;
            this.charWidth = charWidth;
        }
    }
}

