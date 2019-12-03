package com.example.beerapi.Model;

public class Yt {
        private String videoUrl;

        public Yt() {
        }

        public Yt(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }
}
