package pokkare.service;

/*
 * ImageProducer
 *
 * Copyright (c) 2000 Ken McCrary, All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies.
 *
 * KEN MCCRARY MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. KEN MCCRARY
 * SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT
 * OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 *  Image producers implement this interface
 *  to provide a way for the Servlet to indicate
 *  the Stream where the image should be output
 *
 */
public interface ImageProducer {
  /**
   *  Output the image to the parameterized output stream. The stream is closed and flushed after writing.
   *
   *  @param stream stream to write image into
   *  @return image type
   */
  public String createImage(OutputStream stream) throws IOException;
  
  /*
   *  Create graphs for specified players according to the scores in the wrapper class.
   *  @param ScoreDataWrapper Wrapper class of all the data objects that need to be output. 
   *  @param maxScore The biggest score of all the players. This is needed in order to determine image height.
   *  @param numberOfGame The number of games in the output. This is needed in order to determine image width.
   */
  public boolean createGraphs(ScoreDataWrapper scoreDatas, int maxScore, int numberOfGames);
}
