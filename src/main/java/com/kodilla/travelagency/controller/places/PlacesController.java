package com.kodilla.travelagency.controller.places;

import com.kodilla.travelagency.api.places.PlaceDTO;
import com.kodilla.travelagency.exceptions.PlaceNotFoundException;
import com.kodilla.travelagency.mapper.places.PlaceMapper;
import com.kodilla.travelagency.service.places.PlacesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Api(tags = "Places")
@CrossOrigin(origins = "*")
@RestController
@Transactional
@RequestMapping("v1/travel/places")
public class PlacesController {
    @Autowired
    private PlaceMapper placeMapper;

    @Autowired
    private PlacesService placesService;

    @ApiOperation(value = "Get All Places")
    @GetMapping(value = "getPlaces")
    public List<PlaceDTO> getPlaces() {
        return placeMapper.mapPlaceListToPlaceDTOList(placesService.getAllPlaces());
    }

    @ApiOperation(value = "Get Place")
    @GetMapping(value = "getPlace/{placeId}")
    public PlaceDTO getPlace(@PathVariable Long placeId) throws PlaceNotFoundException {
        return placeMapper.mapPlaceToPlaceDTO(placesService.findPlaceById(placeId));
    }

    @ApiOperation(value = "Get Place By Name")
    @GetMapping(value = "getPlace/{placeName}")
    public PlaceDTO getPlaceByName(@PathVariable String placeName) throws PlaceNotFoundException {
        return placeMapper.mapPlaceToPlaceDTO(placesService.findPlaceByPlaceName(placeName));
    }

    @ApiOperation(value = "Save Place")
    @PostMapping(value = "savePlace", consumes = APPLICATION_JSON_VALUE)
    public PlaceDTO savePlace(@RequestBody PlaceDTO placeDTO) {
        return placeMapper.mapPlaceToPlaceDTO(placesService.savePlace(placeMapper.mapPlaceDTOToPlace(placeDTO)));
    }

    @ApiOperation(value = "Update Place")
    @PutMapping(value = "updatePlace", consumes = APPLICATION_JSON_VALUE)
    public PlaceDTO updatePlace(@RequestBody PlaceDTO placeDTO) {
        return placeMapper.mapPlaceToPlaceDTO(placesService.savePlace(placeMapper.mapPlaceDTOToPlace(placeDTO)));
    }

    @ApiOperation(value = "Delete Place")
    @DeleteMapping(value = "deletePlace/{placeId}")
    public void deletePlace(@PathVariable Long placeId) throws PlaceNotFoundException {
        placesService.deletePlace(placeId);
    }

    @ApiOperation(value = "Get Places By Country")
    @GetMapping(value = "getPlaces/country")
    public List<PlaceDTO> getPlacesByCountry(@RequestBody PlaceDTO placeDTO) {
        return placeMapper.mapPlaceListToPlaceDTOList(placesService.findPlacesByCountry(placeDTO.getCountry()));
    }

    @ApiOperation(value = "Get Places By City")
    @GetMapping(value = "getPlaces/city")
    public List<PlaceDTO> getPlacesByNearestCity(@RequestBody PlaceDTO placeDTO) {
        return placeMapper.mapPlaceListToPlaceDTOList(placesService.findPlacesByNearestCity(placeDTO.getNearestCity()));
    }

    @ApiOperation(value = "Get Places By City And Distance")
    @GetMapping(value = "getPlaces/cityAndDistance")
    public List<PlaceDTO> getPlacesByNearestCityAndDistance(@RequestBody PlaceDTO placeDTO) {
        return placeMapper.mapPlaceListToPlaceDTOList(
                placesService.findPlacesByNearestCityAnddistance(
                        placeDTO.getNearestCity(), placeDTO.getDistanceFromNearestCity()
                )
        );
    }

    @ApiOperation(value = "Get Places By Cost")
    @GetMapping(value = "getPlaces/cost")
    public List<PlaceDTO> getPlacesByCost(@RequestBody PlaceDTO placeDTO) {
        return placeMapper.mapPlaceListToPlaceDTOList(placesService.findPlacesByCost(placeDTO.getCostToEnter()));
    }

    @ApiOperation(value = "Get Places By Monument Status")
    @GetMapping(value = "getPlaces/monument")
    public List<PlaceDTO> getPlacesByMonumentStatus(@RequestBody PlaceDTO placeDTO) {
        return placeMapper.mapPlaceListToPlaceDTOList(placesService.findPlacesByIsMonument(placeDTO.getIsMonument()));
    }

    @ApiOperation(value = "Get Places Using All Parameters")
    @GetMapping(value = "getPlaces/params")
    public List<PlaceDTO> getPlacesUsingAllParams(@RequestBody PlaceDTO placeDTO) {
        return placeMapper.mapPlaceListToPlaceDTOList(placesService.findPlaceUsingAllParameters(placeDTO));
    }
}
