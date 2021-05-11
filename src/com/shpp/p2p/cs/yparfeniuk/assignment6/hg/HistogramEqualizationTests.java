package com.shpp.p2p.cs.yparfeniuk.assignment6.hg;

import com.shpp.cs.a.simple.SimpleProgram;
import com.shpp.p2p.cs.yparfeniuk.assignment6.hg.teest.*;

public class HistogramEqualizationTests extends SimpleProgram {
    public void init() {
        TeestRunnerPanel panel = new TeestRunnerPanel(new TeestSuite[]{new TeestSuite("histogramFor Tests", new TeestCase[]{new TeestCase() {
            public String getName() {
                return "Result array should have proper number of entries.";
            }

            public boolean runTest() {
                int[] result = HistogramEqualizationLogic.histogramFor(new int[10][10]);
                return result != null && result.length == 256;
            }
        }, new TeestCase() {
            public String getName() {
                return "Testing image of all black pixels.";
            }

            public boolean runTest() {
                int[][] image = new int[137][42];
                int[] result = HistogramEqualizationLogic.histogramFor(image);
                if (result != null && result.length == 256) {
                    if (result[0] != 5754) {
                        return false;
                    } else {
                        for (int i = 1; i < 256; ++i) {
                            if (result[i] != 0) {
                                return false;
                            }
                        }

                        return true;
                    }
                } else {
                    return false;
                }
            }
        }, new TeestCase() {
            public String getName() {
                return "Testing image of all white pixels.";
            }

            public boolean runTest() {
                int[][] image = new int[137][42];

                int i;
                for (int result = 0; result < image.length; ++result) {
                    for (i = 0; i < image[result].length; ++i) {
                        image[result][i] = 255;
                    }
                }

                int[] var4 = HistogramEqualizationLogic.histogramFor(image);
                if (var4 != null && var4.length == 256) {
                    if (var4[255] != 5754) {
                        return false;
                    } else {
                        for (i = 0; i < 255; ++i) {
                            if (var4[i] != 0) {
                                return false;
                            }
                        }

                        return true;
                    }
                } else {
                    return false;
                }
            }
        }, new TeestCase() {
            public String getName() {
                return "Testing image with one pixel of each color.";
            }

            public boolean runTest() {
                int[][] image = new int[1][256];

                for (int result = 0; result < 256; image[0][result] = result++) {
                    ;
                }

                int[] var4 = HistogramEqualizationLogic.histogramFor(image);
                if (var4 != null && var4.length == 256) {
                    for (int i = 0; i < 256; ++i) {
                        if (var4[i] != 1) {
                            return false;
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        }, new TeestCase() {
            public String getName() {
                return "Testing image with half black pixels and half white pixels.";
            }

            public boolean runTest() {
                int[][] image = new int[256][256];

                int i;
                for (int result = 0; result < 256; ++result) {
                    for (i = 0; i < 256; ++i) {
                        image[result][i] = result % 2 == 0 ? 0 : 255;
                    }
                }

                int[] var4 = HistogramEqualizationLogic.histogramFor(image);
                if (var4 != null && var4.length == 256) {
                    if (var4[255] != '耀') {
                        return false;
                    } else if (var4[0] != '耀') {
                        return false;
                    } else {
                        for (i = 1; i < 255; ++i) {
                            if (var4[i] != 0) {
                                return false;
                            }
                        }

                        return true;
                    }
                } else {
                    return false;
                }
            }
        }}), new TeestSuite("cumulativeSumFor Tests", new TeestCase[]{new TeestCase() {
            public String getName() {
                return "Result array should have proper number of entries.";
            }

            public boolean runTest() {
                int[] fakeData = new int[256];
                fakeData[0] = 1;
                int[] result = HistogramEqualizationLogic.cumulativeSumFor(fakeData);
                return result != null && result.length == 256;
            }
        }, new TeestCase() {
            public String getName() {
                return "Testing sum of histogram of all 1s.";
            }

            public boolean runTest() {
                int[] fakeData = new int[256];

                for (int result = 0; result < fakeData.length; ++result) {
                    fakeData[result] = 1;
                }

                int[] var4 = HistogramEqualizationLogic.cumulativeSumFor(fakeData);
                if (var4 != null && var4.length == 256) {
                    for (int i = 0; i < var4.length; ++i) {
                        if (var4[i] != i + 1) {
                            return false;
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        }, new TeestCase() {
            public String getName() {
                return "Testing histogram of all black pixels.";
            }

            public boolean runTest() {
                int[] fakeData = new int[256];
                fakeData[0] = 256;
                int[] result = HistogramEqualizationLogic.cumulativeSumFor(fakeData);
                if (result != null && result.length == 256) {
                    for (int i = 0; i < result.length; ++i) {
                        if (result[i] != 256) {
                            return false;
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        }, new TeestCase() {
            public String getName() {
                return "Testing histogram of only white and black pixels.";
            }

            public boolean runTest() {
                int[] fakeData = new int[256];
                fakeData[0] = 100;
                fakeData[255] = 100;
                int[] result = HistogramEqualizationLogic.cumulativeSumFor(fakeData);
                if (result != null && result.length == 256) {
                    if (result[0] != 100) {
                        return false;
                    } else {
                        for (int i = 1; i < result.length - 1; ++i) {
                            if (result[i] != 100) {
                                return false;
                            }
                        }

                        return result[255] == 200;
                    }
                } else {
                    return false;
                }
            }
        }, new TeestCase() {
            public String getName() {
                return "Testing histogram of increasing frequencies.";
            }

            public boolean runTest() {
                int[] fakeData = new int[256];

                for (int result = 0; result < fakeData.length; fakeData[result] = result++) {
                    ;
                }

                int[] var4 = HistogramEqualizationLogic.cumulativeSumFor(fakeData);
                if (var4 != null && var4.length == 256) {
                    for (int i = 1; i < var4.length; ++i) {
                        if (var4[i] != i * (i + 1) / 2) {
                            return false;
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        }}), new TeestSuite("totalPixelsIn Tests", new TeestCase[]{new TeestCase() {
            public String getName() {
                return "Test of 40 x 40 image.";
            }

            public boolean runTest() {
                return HistogramEqualizationLogic.totalPixelsIn(new int[40][40]) == 1600;
            }
        }, new TeestCase() {
            public String getName() {
                return "Test of 50 x 30 image.";
            }

            public boolean runTest() {
                return HistogramEqualizationLogic.totalPixelsIn(new int[50][30]) == 1500;
            }
        }, new TeestCase() {
            public String getName() {
                return "Test of 30 x 50 image.";
            }

            public boolean runTest() {
                return HistogramEqualizationLogic.totalPixelsIn(new int[30][50]) == 1500;
            }
        }, new TeestCase() {
            public String getName() {
                return "Test of 1 x 1 image.";
            }

            public boolean runTest() {
                return HistogramEqualizationLogic.totalPixelsIn(new int[1][1]) == 1;
            }
        }, new TeestCase() {
            public String getName() {
                return "Test of 1 x 8 image.";
            }

            public boolean runTest() {
                return HistogramEqualizationLogic.totalPixelsIn(new int[1][8]) == 8;
            }
        }, new TeestCase() {
            public String getName() {
                return "Test of 8 x 1 image.";
            }

            public boolean runTest() {
                return HistogramEqualizationLogic.totalPixelsIn(new int[8][1]) == 8;
            }
        }})});
        this.add(panel);
        panel.runTests();
    }
}
