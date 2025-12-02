package edu.project4;

import edu.project4.PostProcessing.GammaLogImageProcessor;
import edu.project4.PostProcessing.ImageProcessor;
import edu.project4.Renderers.FractalRenderer;
import edu.project4.Renderers.MultiThreadFractalRenderer;
import edu.project4.Renderers.SingleThreadFractalRenderer;
import edu.project4.Transformations.DiscTransformation;
import edu.project4.Transformations.HeartTransformation;
import edu.project4.Transformations.HorseShoeTransformation;
import edu.project4.Transformations.PopcornTransformation;
import edu.project4.Transformations.SinusoidTransformation;
import edu.project4.Transformations.SphereTransformation;
import edu.project4.Transformations.SwirlTransformation;
import edu.project4.Transformations.Transformation;
import edu.project4.shared.FractalImage;
import edu.project4.shared.ImageFormat;
import edu.project4.shared.ImageUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import edu.project4.shared.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Project4Tests {

    @Test
    void singleThreadSmallTest() {
        FractalImage fractalImage = FractalImage.create(1920, 1080);
        FractalRenderer renderer = new SingleThreadFractalRenderer();

        // Setting functions
        List<Transformation> transformationArrayList = new ArrayList<>();
        transformationArrayList.add(new SphereTransformation());

        // rendering
        Random random = new Random();
        fractalImage = renderer.render(fractalImage, transformationArrayList, 30, 60000, 2, random.nextLong());

        // post processing
        ImageProcessor imageProcessor = new GammaLogImageProcessor();
        imageProcessor.process(fractalImage);

        // save to filesystem
        ImageUtils.save(fractalImage, "project4/singleSmallImage.png", ImageFormat.PNG);
    }

    @Test
    void multiThreadSmallTest() {
        FractalImage fractalImage = FractalImage.create(1920, 1080);
        FractalRenderer renderer = new MultiThreadFractalRenderer(4);

        // Setting functions
        List<Transformation> transformationArrayList = new ArrayList<>();
        transformationArrayList.add(new SphereTransformation());

        // rendering
        Random random = new Random();
        fractalImage = renderer.render(fractalImage, transformationArrayList, 30, 60000, 2, random.nextLong());

        // post processing
        ImageProcessor imageProcessor = new GammaLogImageProcessor();
        imageProcessor.process(fractalImage);

        // save to filesystem
        ImageUtils.save(fractalImage, "project4/multiSmallImage.png", ImageFormat.PNG);
    }

    @Test
    void singleThreadBigTest() {
        FractalImage fractalImage = FractalImage.create(2560, 1440);
        FractalRenderer renderer = new SingleThreadFractalRenderer();

        // Setting functions
        List<Transformation> transformationArrayList = new ArrayList<>();
        transformationArrayList.add(new SphereTransformation());
        transformationArrayList.add(new HorseShoeTransformation());
        transformationArrayList.add(new PopcornTransformation());

        // rendering
        Random random = new Random();
        fractalImage = renderer.render(fractalImage, transformationArrayList, 30, 600000, 4, random.nextLong());

        // post processing
        ImageProcessor imageProcessor = new GammaLogImageProcessor();
        imageProcessor.process(fractalImage);

        // save to filesystem
        ImageUtils.save(fractalImage, "project4/singleBigImage.png", ImageFormat.PNG);
    }

    @Test
    void multiThreadBigTest() {
        FractalImage fractalImage = FractalImage.create(2560, 1440);
        FractalRenderer renderer = new MultiThreadFractalRenderer(4);

        // Setting functions
        List<Transformation> transformationArrayList = new ArrayList<>();
        transformationArrayList.add(new SphereTransformation());
        transformationArrayList.add(new HorseShoeTransformation());
        transformationArrayList.add(new PopcornTransformation());

        // rendering
        Random random = new Random();
        fractalImage = renderer.render(fractalImage, transformationArrayList, 30, 600000, 4, random.nextLong());

        // post processing
        ImageProcessor imageProcessor = new GammaLogImageProcessor();
        imageProcessor.process(fractalImage);

        // save to filesystem
        ImageUtils.save(fractalImage, "project4/multiBigImage.png", ImageFormat.PNG);
    }

    @Test
    void transformationTest() {

        //given
        Point point = new Point(1,1);

        //when
        Point actualSwirl = new SwirlTransformation().apply(point,1,1);
        Point actualSphere = new SphereTransformation().apply(point,1,1);
        Point actualSinusoid = new SinusoidTransformation().apply(point,1,1);
        Point actualPopcorn = new PopcornTransformation().apply(point,1,1);
        Point actualHorseShoe = new HorseShoeTransformation().apply(point,1,1);
        Point actualHeart = new HeartTransformation().apply(point,1,1);
        Point actualDisc = new DiscTransformation().apply(point,1,1);

        //then
        assertEquals(actualSwirl,new Point(1.325444263372824,0.4931505902785393));
        assertEquals(actualSphere,new Point(0.5,0.5));
        assertEquals(actualSinusoid,new Point(0.8414709848078965,0.8414709848078965));
        assertEquals(actualPopcorn,new Point(0.8579357128540236,0.8579357128540236));
        assertEquals(actualHorseShoe,new Point(0.0,1.414213562373095));
        assertEquals(actualHeart,new Point(1.2671621313307992,-0.6279332232978174));
        assertEquals(actualDisc,new Point(-0.24097563321246931,-0.06656383551035391));
    }
}
