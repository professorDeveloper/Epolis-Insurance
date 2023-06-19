package com.azamovhudstc.epolisinsurance.customUI;

public interface DrawableClickListener {

    public static enum DrawablePosition { TOP, BOTTOM, LEFT, RIGHT };
    public void onClick(DrawablePosition target); 
    }
