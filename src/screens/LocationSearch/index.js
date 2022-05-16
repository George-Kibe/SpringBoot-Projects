import { GooglePlacesAutocomplete } from 'react-native-google-places-autocomplete';
import { View, Text, TextInput, FlatList, Pressable } from 'react-native'
import React, {useState} from 'react'
import styles from './styles'
import { useNavigation } from '@react-navigation/native'
import SuggestionsRow from './SuggestionsRow';

import searchResults from '../../../assets/data/search'

const LocationSearchScreen = () => {
  const navigation = useNavigation()
  const [inputText, setInputText] = useState("")
  const APIKEY="AIzaSyDnMX-u2MkG1b4UcuakpWfMNK7OQmGmHa4"

  return (
    <View style={styles.container}>       
      <GooglePlacesAutocomplete placeholder='Search Properties by Location...'
        styles={styles.textInput}
        onPress={(data, details = null) => {
        // 'details' is provided when fetchDetails = true
        console.warn(data, details);
        navigation.navigate("Accommodation Details")
        }}
        query={{
          key: APIKEY,
          
          language: 'en',
          // types:"(cities)", 
        }}
        renderRow={(item) => <SuggestionsRow item={item}/>}
        fetchDetails
        suppressDefaultStyles
      />
    </View>
  )
}

export default LocationSearchScreen;