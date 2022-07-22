package models.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Image {
    @Column(nullable = false)
    protected String title;
    @Column(nullable = false)
    protected String filename;
    protected int width;
    protected int height;

    public Image(String title, String filename, int width, int height) {
        this.title = title;
        this.filename = filename;
        this.width = width;
        this.height = height;
    }

    public Image() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (width != image.width) return false;
        if (height != image.height) return false;
        if (!title.equals(image.title)) return false;
        return filename.equals(image.filename);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + filename.hashCode();
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "title='" + title + '\'' +
                ", filename='" + filename + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}