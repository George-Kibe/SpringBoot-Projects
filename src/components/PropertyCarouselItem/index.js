import { View, Text, Image, useWindowDimensions, Pressable } from 'react-native'
import React from 'react'
import styles from "./styles"
import { useNavigation } from '@react-navigation/native';

const PropertyCarouselItem = ({property}) => {
  const navigation = useNavigation()
  const goToProperty = () =>{
    navigation.navigate("Detailed Property", {propertyId:property.id});
  }

  const { height, width } = useWindowDimensions();
  return (
    <Pressable onPress={goToProperty} style={[styles.container, {width:width-60}]} key={property.id}>
      <View style={styles.innerContainer}>
        <Image style={styles.image}
          source={{uri:property.image}}/>
        <View style={{marginHorizontal:10, width:width/2-20}}>
          <Text style={styles.bedrooms}>{property.bedroom}-Bedroom {property.title}</Text>
          <Text style={styles.description} numberOfLines={2} >
          {property.description}
          </Text>
          <Text style={styles.prices}>
              <Text style={styles.newPrice}> ${property.newPrice} </Text>
              Per Year
          </Text>
        </View>
      </View>
    </Pressable>
  )
};

export default PropertyCarouselItem;