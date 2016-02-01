package com.example.ainalia.shop;

/**
 * Created by Ainalia on 2016/1/27.
 */
public class ImagesSourceUrl {

        private int startcount;
        private int finalount;
        private String[] imageThumbUrls;

        public ImagesSourceUrl(int start ,int fianl)
        {
                setCount(start,fianl);
                createUrls();
        }


        public String[] getImageThumbUrls()
        {
               return imageThumbUrls;
        }

        private void createUrls()
        {
            imageThumbUrls = new String[getCount()];
            for(int i= startcount; i<= finalount; i++)
            {
                imageThumbUrls[i-1]=new String("http://wfm.yctec.com.tw/Prod/A0000100"+createName(i)+".jpg");
            }

        }

        private String createName(int i)
        {
                String s;

                if(i<10)
                        s="00"+Integer.toString(i);
                else if(i<100)
                        s="0"+Integer.toString(i);
                else
                        s=Integer.toString(i);

               return s;
        }

        public void setCount(int start ,int fianl)
        {
                startcount = start;
                finalount = fianl;
        }

        public int getCount()
        {
                return this.finalount - this.startcount+1;
        }
}
