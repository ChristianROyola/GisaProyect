package ups.edu.ec.gisab.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import ups.edu.ec.gisab.dao.VideoDAO;
import ups.edu.ec.gisab.modelo.Video;


@ManagedBean
@ViewScoped
public class VideoBean {

	
	@Inject
	private VideoDAO vdao;
	
	
	private Video video;
	
	private List<Video> videos; 
	
	@PostConstruct
	private void init() {
		video =new Video();
		videos = vdao.getVideos();
	}
	
	
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	
	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}


	public String doRead() {
		Video video = vdao.read(this.video.getVid_id());
		System.out.println(video.toString());
		
		return null;
	}
	
	public String doList() {
		List<Video> videos = vdao.getVideos();
		for (Video video : videos) {
			System.out.println(video.toString());
		}
		
		return null;
	}
	
	
}
