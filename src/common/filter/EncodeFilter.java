package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * 
 *  Filter의 생명주기
 *  1. init(FilterConfig)
 *  2. doFilter(ServletRequest, ServletResponse, FilterChain) : 필터 전처리, 후처리 로직작성
 *  3. destroy()
 *
 */
public class EncodeFilter implements Filter{
	
	private FilterConfig fConfig;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
//		System.out.println("---------EncodingFilter.init호출--------");
		this.fConfig = filterConfig;
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//전처리: servlet 요청전
		//인코딩처리
		String encodeType = fConfig.getInitParameter("encodeType");
		
		request.setCharacterEncoding(encodeType);
//		System.out.println("["+encodeType+"] 인코딩처리됨@EncodeFilter");
		
		//다음 필터의 dofilter메소드를 호출: 마지막 필터라면, servlet호출!
		chain.doFilter(request, response);
		
		//후처리
		
	}

	@Override
	public void destroy() {
//		System.out.println("---------EncodingFilter.destroy호출--------");
	}

}
