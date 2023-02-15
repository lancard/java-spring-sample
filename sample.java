@Validated
@RestController
@RequestMapping("/api")
public class NewsRestController {
	@Autowired
	TestService testService;

	@CacheControl(maxAge = 10, policy = {})
	@Operation(summary = "리스트 가져오기")
	@RequestMapping(value = "/getList", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ReturnJsonDto getNewsList(@Valid ListParam params) {
		return ReturnUtil.createReturnObject(true, "성공", testService.getList(params));
	}

}
